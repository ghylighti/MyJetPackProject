package MyView;



public interface RecyclerLoadMore  {
    void loadMore(int page,String id);
    void loadMore(int page);
    void update(RefreshCallBack callBack,String id);
    void update(RefreshCallBack callBack);
}
