package com.example.dictionary.model.rx

import io.reactivex.rxjava3.core.Scheduler

interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}
