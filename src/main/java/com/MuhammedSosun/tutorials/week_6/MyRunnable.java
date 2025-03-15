package com.MuhammedSosun.tutorials.week_6;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -Sayi" + i);
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println("Kesintiye ugradı");
            }
        }
    }
}
class RunnableExample{
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        Thread thread3 = new Thread(new MyRunnable());


        thread1.start();
        thread2.start();
        thread2.join();
        thread3.start();
    }
}
