package com.example.meeera.browserapp.Service;


import android.util.Log;

import com.example.meeera.browserapp.Model.ArticleDetail;
import com.example.meeera.browserapp.Model.ArticleEvent;
import com.example.meeera.browserapp.Model.Articles;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by meeera on 13/10/17.
 */

public class NetworkCall {
    public getData data;
    public List<ArticleDetail> articles = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    Observable<Articles> business = retrofit.create(NewService.class).getNews("business-insider", "a7822d7c58dd4e50b1ebb3e1b69486b6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    Observable<Articles> espn = retrofit.create(NewService.class).getNews("espn", "a7822d7c58dd4e50b1ebb3e1b69486b6").subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    public Observable<ArticleEvent> article = Observable.zip(business, espn, new BiFunction<Articles, Articles, ArticleEvent>() {
        @Override
        public ArticleEvent apply(Articles articles, Articles articles2) throws Exception {
            return new ArticleEvent(articles.getArticles());
        }
    });

    public NetworkCall(getData data) {
        this.data = data;
    }

    public void getSub() {
        article.subscribe(new Observer<ArticleEvent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArticleEvent articleEvent) {
                articles = articleEvent.getArticle();
                data.getDatas(articles);
                Log.d("AMITAMITAMIT","Completed"+articleEvent.getArticle());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.d("AMITAMITAMIT","Completed"+articles.get(0).getTitle());
            }
        });
    }

   public interface getData {
        void getDatas(List<ArticleDetail> articles);
    }
}
