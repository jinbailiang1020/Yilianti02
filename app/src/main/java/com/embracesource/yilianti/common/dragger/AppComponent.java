package com.embracesource.yilianti.common.dragger;

import com.embracesource.yilianti.ui.homepage.diagnosis.ApplyDiagnosisActivity01;
import com.embracesource.yilianti.ui.homepage.diagnosis.DiagnosisPictureActivity;

/**
 * http://blog.csdn.net/javine/article/details/52787990
 * AppComponent类是一种桥梁，MyPresenter类通过AppComponet注入到MainActivity中去的。
 * 我们将会在MainActivity中看到注入是通过DaggerAppComponent类来执行的，而这是一个我从来没有编写过的类..
 * .它就是dagger的插件给我们自动生成的，当然是根据AppComponet来生成的。
 */
//@Singleton
//@Component(dependencies = ApplyDiagnosisViewModelCallBack.class, modules = AppModule.class)
public interface AppComponent {

    void inject(DiagnosisPictureActivity activity);

    void inject(ApplyDiagnosisActivity01 applyDiagnosisActivity01);

//    void inject(ApplyDiagnosisActivity02 applyDiagnosisActivity02);

//    Context context();  // 提供Applicaiton的Context

//    ThreadExecutor threadExecutor();   // 线程池

//    ApiService apiService();  // 所有Api请求的管理类

//    SpfManager spfManager();  // SharedPreference管理类

//    DBManager dbManager();  // 数据库管理类
}
