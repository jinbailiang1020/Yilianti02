package com.embracesource.yilianti.common.dragger;

/**
 * http://blog.csdn.net/javine/article/details/52787990
 *  MyModule类 用来提供依赖，里面定义一些用@Provides注解的以provide开头的方法。MainActivity中使用的MyPresenter对象就是在此处被实例化的。
 */
//@Module
public class AppModule {
    public AppModule() {
    }

 /*   public AppModule(ApplyDiagnosisViewModelCallBack applyDiagnosisViewModelCallBack) {
        this.applyDiagnosisViewModelCallBack = applyDiagnosisViewModelCallBack;
    }*/

//    private ApplyDiagnosisViewModelCallBack applyDiagnosisViewModelCallBack;

/*    @Provides
    public DiagnosisPictureViewModel provideDiagnosisPictureViewModel(){
        return new DiagnosisPictureViewModel(this);
    }*/
  /*  @Provides
    public ApplyDiagnosisViewModel01 provideApplyDiagnosisViewModel01(){
        return new ApplyDiagnosisViewModel01();
    }*/

/*    @Provides
    public ApplyDiagnosisViewModel02 provideApplyDiagnosisViewModel02(ApplyDiagnosisViewModelCallBack applyDiagnosisViewModelCallBack){
        return new ApplyDiagnosisViewModel02(applyDiagnosisViewModelCallBack);
    }*/

/*    private IView mainView;

    public MyModule(IView mainView){
        this.mainView = mainView;
    }

    @Provides
    public MyPresenter provideMyPresenter(){
        return new MyPresenter(mainView);
    }*/




//    private final AppContext application;

//    public AppModule(AppContext application) {
//        this.application = application;
//    }

 /*   @Provides
    @Singleton
    Context provideApplicationContext() { 
       return application;
    }*/

//    @Provides
//    @Singleton
//    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
//        return jobExecutor;
//    }

/*    @Provides
    @Singleton
    ApiService providesApiService(RetrofitManager retrofitManager) {
        return retrofitManager.getService();
    }*/

/*    @Provides
    @Singleton
    SpfManager provideSpfManager() {
        return new SpfManager(application);
    }

    @Provides
    @Singleton
    DBManager provideDBManager() {
        return new DBManager(application);
    }*/
}
