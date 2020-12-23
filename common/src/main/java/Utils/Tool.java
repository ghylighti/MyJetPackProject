package Utils;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Binder;
import android.os.Looper;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;



public class Tool {

    private static boolean sIs2G = false;
    private static String aolidayVersion;
    private static Integer aolidayVersionCode;
    private static volatile Boolean isSmallScree = null;
    private static volatile DisplayMetrics metrics = null;



    public static String replceHighLight(String str, String color) throws PatternSyntaxException {
         if (str != null && str.length() > 0) {
            return str.replaceAll("ColorStart", "<font color=\"" + color + "\">").replaceAll("ColorEnd", "</font>");
        } else {
            return str;
        }
    }

    /**
     * 返回是否为2G网络
     *
     * @return true为2G
     */
    public static boolean is2GNetWork() {
        return sIs2G;
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static int convertInteger(String valueStr) {
        int rate;
        try {
            rate = Integer.valueOf(valueStr);
        } catch (Exception e) {
            rate = 0;
        }
        return rate;
    }

    public static long convertLong(String valueStr) {
        long rate;
        try {
            rate = Long.valueOf(valueStr);
        } catch (Exception e) {
            rate = 0;
        }
        return rate;
    }

    public static boolean convertBoolean(String valueStr) {
        boolean rate;
        try {
            rate = Boolean.parseBoolean(valueStr);
        } catch (Exception e) {
            rate = false;
        }
        return rate;
    }

    public static boolean isNetworkEquals(NetworkInfo oldNetInfo, NetworkInfo info) {
        return (oldNetInfo != null && info != null && oldNetInfo.getState() == info.getState()
                && (TextUtils.equals(oldNetInfo.getExtraInfo(), info.getExtraInfo()))
                && info.getSubtype() == oldNetInfo.getSubtype() && info.getType() == oldNetInfo.getType())
                || (oldNetInfo == null && info == null);
    }

    /**
     * 扫描、刷新相册
     */
    public static void scanPhotos(String filePath, Context context) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    public static void deleteFromGallery(String path, Context context) {
        context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                MediaStore.Images.Media.DATA + " = ?", new String[]{path});
    }

    public static String getAolidayVersion(Context context) {
        if (TextUtils.isEmpty(aolidayVersion)) {
            retrieveAppStorePkgInfo(context);
        }
        return aolidayVersion;
    }

    public static int getAolidayVersionCode(Context context) {
        if (TextUtils.isEmpty(aolidayVersion)) {
            retrieveAppStorePkgInfo(context);
        }
        return aolidayVersionCode;
    }

    private static void retrieveAppStorePkgInfo(Context context) {
        try {
            String packageName = context.getPackageName();
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            if (pi != null) {
                aolidayVersion = "" + pi.versionName;
                aolidayVersionCode = pi.versionCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int computeUsableHeight(View view) {
        Rect r = new Rect();
        view.getWindowVisibleDisplayFrame(r);
        return r.bottom - r.top;
    }

    public static Date stringToDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static boolean isExternalStorageMounted() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static boolean isSmallScreenSize(Context mContext) {
        if (isSmallScree == null) {
            synchronized (Tool.class) {
                if (isSmallScree == null) {
                    WindowManager window = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    Display display = window.getDefaultDisplay();
                    DisplayMetrics metric = new DisplayMetrics();
                    display.getMetrics(metric);
                    isSmallScree = metric.widthPixels < 490;
                }
            }
        }
        return isSmallScree;
    }

    public static DisplayMetrics getDisplayMetrics(Context mContext) {
        if (metrics == null) {
            synchronized (Tool.class) {
                if (metrics == null) {
                    WindowManager window = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    Display display = window.getDefaultDisplay();
                    metrics = new DisplayMetrics();
                    display.getMetrics(metrics);

                }
            }
        }
        return metrics;
    }





    public static void goToUri(Context mContext, String url) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        intent.setPackage(mContext.getPackageName());
        PackageManager pm = mContext.getApplicationContext().getPackageManager();
        // 查询是否支持该schema
        List<ResolveInfo> listActivities = pm.queryIntentActivities(intent, 0);
        if (listActivities != null && !listActivities.isEmpty()) {
            mContext.startActivity(intent);
        }
    }


    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).matches();
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]");
        return pattern.matcher(str.trim()).matches();
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isEnglish(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[A-Za-z\\s]+");
        return pattern.matcher(str.trim()).matches();
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean validateNickName(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9A-Za-z\\u4E00-\\u9FBF\\s]+");
        return pattern.matcher(str.trim()).matches();
    }

    public static boolean isLocationServiceOpen(final Context context) {
        boolean gps = false;
        boolean network = false;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
        }
        if (network) {
            return true;
        }

        try {
            gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
        }
        return gps;
    }

    public static boolean isThirdLogIn(int type) {
        return type == 1 || type == 2 || type == 3;
    }

    /**
     * 过滤应用名称转为拼音后的非法字符
     *
     * @return appname
     */
    public static String filterAppName(String py) {
        try {
            Pattern p = Pattern.compile("[^a-zA-Z0-9]");
            Matcher m = p.matcher(py);
            return m.replaceAll("").trim();
        } catch (Exception e) {
            // e.printStackTrace();
            return py;
        }
    }

    public static Calendar getLocalCalendar(String lag, int safetyHour, int safetyMin) {
        String lagFirst = lag;
        String subHours = "";
        TimeZone localTimeZone;
        int minutes = 0;
        if (lag.contains(".")) {
            String[] split = lag.split("\\.");
            lagFirst = split[0];
            if (split.length > 1) {
                subHours = split[1];
            }
        }
        if (lag.startsWith("-") || lag.startsWith("+")) {
            localTimeZone = TimeZone.getTimeZone("GMT" + lagFirst);
        } else {
            localTimeZone = TimeZone.getTimeZone("GMT" + "+" + lagFirst);
        }

        if (!subHours.equals("")) {
            if (lag.startsWith("-")) {
                subHours = "-0." + subHours;
            } else {
                subHours = "0." + subHours;
            }

            Float hour = Float.valueOf(subHours);
            Float m = (hour * 60);
            minutes = m.intValue();
        }

        Calendar cal = Calendar.getInstance(localTimeZone);
        cal.add(Calendar.HOUR, safetyHour);
        cal.add(Calendar.MINUTE, safetyMin);
        cal.add(Calendar.MINUTE, minutes);
        return cal;
    }

    public static String[] getDateStrList(String lag, int safetyHour, int safetyMin, int count) {
        Calendar cal = getLocalCalendar(lag, safetyHour, safetyMin);
        String[] dateStrList = new String[count];
        for (int i = 0; i < count; i++) {
            String dayStr = getWeekOfDate(cal, i);
            dateStrList[i] = dayStr;

        }

        return dateStrList;
    }

    public static String getWeekOfDate(Calendar cal, int dayindex) {
        Calendar cal1 = (Calendar) cal.clone();
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        cal1.add(Calendar.DAY_OF_YEAR, dayindex);
        int w = cal1.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        int day = cal1.get(Calendar.DATE);
        int month = cal1.get(Calendar.MONTH) + 1;
        //        int year = cal1.get(Calendar.YEAR);

        return month + "月" + day + "日" + weekDays[w];//year + "年" +
    }


    public static boolean isAppInstalled(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }

        return packageInfo!=null && packageInfo.applicationInfo.enabled;
    }

    public static String getDiviceUuid(Context context) {
        String uniqueId = "000000";
        try {
            if(context!=null&&context.getSystemService(Context.TELEPHONY_SERVICE)!=null)
            {
                final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                final String tmDevice, tmSerial, tmPhone, androidId;
                tmDevice = "" + tm.getDeviceId();
                tmSerial = "" + tm.getSimSerialNumber();
                androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
                UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
                uniqueId = deviceUuid.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uniqueId;
    }



    /**
     * 验证信用卡号
     */
    public static boolean isValidCard(String sCard) {
        String reCard = "\\d{12,19}";
        Pattern com = Pattern.compile(reCard);
        Matcher mat = com.matcher(sCard);
        return mat.find() && isLuhn(sCard);
    }

    /**
     * luhn算法
     */
    public static boolean isLuhn(String strNum) {
        int oddSum = 0;
        int evenSum = 0;
        boolean isOdd = true;
        for (int i = strNum.length() - 1; i >= 0; i--) {
            char cNum = strNum.charAt(i);
            int num = Integer.parseInt(cNum + "");
            if (isOdd) {
                oddSum += num;
            } else {
                num = num * 2;
                if (num > 9) {
                    num = num % 10 + 1;
                }
                evenSum = evenSum + num;
            }
            isOdd = !isOdd;
        }
        return ((evenSum + oddSum) % 10 == 0);
    }


    public static boolean isDebugable(Context context) {
        boolean logEnable;
        try {
            ApplicationInfo info = context.getApplicationInfo();
            logEnable = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            logEnable = false;
        }
        return logEnable;
    }



    public static boolean isDNSOk(String hostName) {
        boolean reachable = false;
        try {
            InetAddress.getByName(hostName);
            reachable = true;
        } catch (Exception e) {

        }

        return reachable;
    }

    public static void startUri(Context mContext, String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            if (Looper.myLooper() != Looper.getMainLooper()) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.setData(Uri.parse(url));
            PackageManager pm = mContext.getApplicationContext().getPackageManager();
            // 查询是否支持该schema
            List<ResolveInfo> listActivities = pm.queryIntentActivities(intent, 0);
            if (listActivities != null && !listActivities.isEmpty()) {
                mContext.startActivity(intent);
            }
        }
    }

    /**
     * 判断 悬浮窗口权限是否打开
     *
     * @param context
     * @return true 允许  false禁止
     */
    public static boolean getAppOps(Context context) {
        try {
            @SuppressLint("WrongConstant")
            Object object = context.getSystemService("appops");
            if (object == null) {
                return false;
            }
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);
            if (method == null) {
                return false;
            }
            Object[] arrayOfObject1 = new Object[3];
            arrayOfObject1[0] = Integer.valueOf(24);
            arrayOfObject1[1] = Integer.valueOf(Binder.getCallingUid());
            arrayOfObject1[2] = context.getPackageName();
            int m = ((Integer) method.invoke(object, arrayOfObject1)).intValue();
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception ex) {

        }
        return false;
    }


}
