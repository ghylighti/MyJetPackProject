/**
  * Copyright 2020 json.cn 
  */
package com.ghy.api.dto.newProjectRootBean;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-11-21 22:46:11
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class NewProjectData implements Parcelable {

    private String apkLink;
    private int audit;
    private String author;
    private boolean canEdit;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String descMd;
    private String envelopePic;
    private boolean fresh;
    private int id;
    private String link;
    private String niceDate;
    private String niceShareDate;
    private String origin;
    private String prefix;
    private String projectLink;
    private long publishTime;
    private int realSuperChapterId;
    private int selfVisible;
    private long shareDate;
    private String shareUser;
    private int superChapterId;
    private String superChapterName;
    private List<Tags> tags;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;

    protected NewProjectData(Parcel in) {
        apkLink = in.readString();
        audit = in.readInt();
        author = in.readString();
        canEdit = in.readByte() != 0;
        chapterId = in.readInt();
        chapterName = in.readString();
        collect = in.readByte() != 0;
        courseId = in.readInt();
        desc = in.readString();
        descMd = in.readString();
        envelopePic = in.readString();
        fresh = in.readByte() != 0;
        id = in.readInt();
        link = in.readString();
        niceDate = in.readString();
        niceShareDate = in.readString();
        origin = in.readString();
        prefix = in.readString();
        projectLink = in.readString();
        publishTime = in.readLong();
        realSuperChapterId = in.readInt();
        selfVisible = in.readInt();
        shareDate = in.readLong();
        shareUser = in.readString();
        superChapterId = in.readInt();
        superChapterName = in.readString();
        title = in.readString();
        type = in.readInt();
        userId = in.readInt();
        visible = in.readInt();
        zan = in.readInt();
    }

    public static final Creator<NewProjectData> CREATOR = new Creator<NewProjectData>() {
        @Override
        public NewProjectData createFromParcel(Parcel in) {
            return new NewProjectData(in);
        }

        @Override
        public NewProjectData[] newArray(int size) {
            return new NewProjectData[size];
        }
    };

    public void setApkLink(String apkLink) {
         this.apkLink = apkLink;
     }
     public String getApkLink() {
         return apkLink;
     }

    public void setAudit(int audit) {
         this.audit = audit;
     }
     public int getAudit() {
         return audit;
     }

    public void setAuthor(String author) {
         this.author = author;
     }
     public String getAuthor() {
         return author;
     }

    public void setCanEdit(boolean canEdit) {
         this.canEdit = canEdit;
     }
     public boolean getCanEdit() {
         return canEdit;
     }

    public void setChapterId(int chapterId) {
         this.chapterId = chapterId;
     }
     public int getChapterId() {
         return chapterId;
     }

    public void setChapterName(String chapterName) {
         this.chapterName = chapterName;
     }
     public String getChapterName() {
         return chapterName;
     }

    public void setCollect(boolean collect) {
         this.collect = collect;
     }
     public boolean getCollect() {
         return collect;
     }

    public void setCourseId(int courseId) {
         this.courseId = courseId;
     }
     public int getCourseId() {
         return courseId;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setDescMd(String descMd) {
         this.descMd = descMd;
     }
     public String getDescMd() {
         return descMd;
     }

    public void setEnvelopePic(String envelopePic) {
         this.envelopePic = envelopePic;
     }
     public String getEnvelopePic() {
         return envelopePic;
     }

    public void setFresh(boolean fresh) {
         this.fresh = fresh;
     }
     public boolean getFresh() {
         return fresh;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setLink(String link) {
         this.link = link;
     }
     public String getLink() {
         return link;
     }

    public void setNiceDate(String niceDate) {
         this.niceDate = niceDate;
     }
     public String getNiceDate() {
         return niceDate;
     }

    public void setNiceShareDate(String niceShareDate) {
         this.niceShareDate = niceShareDate;
     }
     public String getNiceShareDate() {
         return niceShareDate;
     }

    public void setOrigin(String origin) {
         this.origin = origin;
     }
     public String getOrigin() {
         return origin;
     }

    public void setPrefix(String prefix) {
         this.prefix = prefix;
     }
     public String getPrefix() {
         return prefix;
     }

    public void setProjectLink(String projectLink) {
         this.projectLink = projectLink;
     }
     public String getProjectLink() {
         return projectLink;
     }

    public void setPublishTime(long publishTime) {
         this.publishTime = publishTime;
     }
     public long getPublishTime() {
         return publishTime;
     }

    public void setRealSuperChapterId(int realSuperChapterId) {
         this.realSuperChapterId = realSuperChapterId;
     }
     public int getRealSuperChapterId() {
         return realSuperChapterId;
     }

    public void setSelfVisible(int selfVisible) {
         this.selfVisible = selfVisible;
     }
     public int getSelfVisible() {
         return selfVisible;
     }

    public void setShareDate(long shareDate) {
         this.shareDate = shareDate;
     }
     public long getShareDate() {
         return shareDate;
     }

    public void setShareUser(String shareUser) {
         this.shareUser = shareUser;
     }
     public String getShareUser() {
         return shareUser;
     }

    public void setSuperChapterId(int superChapterId) {
         this.superChapterId = superChapterId;
     }
     public int getSuperChapterId() {
         return superChapterId;
     }

    public void setSuperChapterName(String superChapterName) {
         this.superChapterName = superChapterName;
     }
     public String getSuperChapterName() {
         return superChapterName;
     }

    public void setTags(List<Tags> tags) {
         this.tags = tags;
     }
     public List<Tags> getTags() {
         return tags;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setUserId(int userId) {
         this.userId = userId;
     }
     public int getUserId() {
         return userId;
     }

    public void setVisible(int visible) {
         this.visible = visible;
     }
     public int getVisible() {
         return visible;
     }

    public void setZan(int zan) {
         this.zan = zan;
     }
     public int getZan() {
         return zan;
     }

    @Override
    public String toString() {
        return "AuthorBookDatas{" +
                "apkLink='" + apkLink + '\'' +
                ", audit=" + audit +
                ", author='" + author + '\'' +
                ", canEdit=" + canEdit +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", collect=" + collect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", descMd='" + descMd + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", fresh=" + fresh +
                ", id=" + id +
                ", link=" + link +
                ", niceDate='" + niceDate + '\'' +
                ", niceShareDate='" + niceShareDate + '\'' +
                ", origin='" + origin + '\'' +
                ", prefix='" + prefix + '\'' +
                ", projectLink='" + projectLink + '\'' +
                ", publishTime=" + publishTime +
                ", realSuperChapterId=" + realSuperChapterId +
                ", selfVisible=" + selfVisible +
                ", shareDate=" + shareDate +
                ", shareUser='" + shareUser + '\'' +
                ", superChapterId=" + superChapterId +
                ", superChapterName='" + superChapterName + '\'' +
                ", tags=" + tags +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", visible=" + visible +
                ", zan=" + zan +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(apkLink);
        dest.writeInt(audit);
        dest.writeString(author);
        dest.writeByte((byte) (canEdit ? 1 : 0));
        dest.writeInt(chapterId);
        dest.writeString(chapterName);
        dest.writeByte((byte) (collect ? 1 : 0));
        dest.writeInt(courseId);
        dest.writeString(desc);
        dest.writeString(descMd);
        dest.writeString(envelopePic);
        dest.writeByte((byte) (fresh ? 1 : 0));
        dest.writeInt(id);
        dest.writeString(link);
        dest.writeString(niceDate);
        dest.writeString(niceShareDate);
        dest.writeString(origin);
        dest.writeString(prefix);
        dest.writeString(projectLink);
        dest.writeLong(publishTime);
        dest.writeInt(realSuperChapterId);
        dest.writeInt(selfVisible);
        dest.writeLong(shareDate);
        dest.writeString(shareUser);
        dest.writeInt(superChapterId);
        dest.writeString(superChapterName);
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeInt(userId);
        dest.writeInt(visible);
        dest.writeInt(zan);
    }
}