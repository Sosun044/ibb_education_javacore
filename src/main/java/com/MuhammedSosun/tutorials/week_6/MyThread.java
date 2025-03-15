package com.MuhammedSosun.tutorials.week_6;

public class MyThread extends Thread{
    public  void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "-Deger " + i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Thread kesintiye uğradı ");
            }
        }
    }
}
class ThreadExample{
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        thread1.start();
        thread2.start();
    }
}