package Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class SchemeUtils {

    public static void handleScheme(Context context, Uri uri)
    {
        String host=uri.getHost();
        String authority = uri.getAuthority();
        String path = uri.getPath();
        String query = uri.getQuery();
        String port = String.valueOf(uri.getPort());
        String scheme = uri.getScheme();
        Intent intent=new Intent();
        intent.setData(uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
