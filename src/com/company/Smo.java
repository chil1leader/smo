package com.company;

public class Smo {
    private int koa = 0, kob = 0, koc = 0;
    private int queue = 0, refusal = 0, leave = 0, total_refusal = 0;
    private int plain1 = 0, plain2 = 0, plain3 = 0;
    private double p1, p2, p3, p4;

    public Smo() {

    }

    public Smo(double p1, double p2, double p3, double p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public void GetInStream() {
        if (Math.random() <= this.p1) {
            if (this.koa == 0) this.koa = 1;
            else if (this.kob == 0) this.kob = 1;
            else if (this.queue == 0) this.queue = 1;
            else this.refusal++;
        }
    }

    public void transitionServiceChannel() {
        if (this.queue == 1) {
            if (this.koa == 0) {
                this.koa = 1;
                this.queue = 0;
            } else if (kob == 0) {
                this.kob = 1;
                this.queue = 0;
            }
        }
    }

    public void serviceChannelC() {
        if (this.koc == 1 & Math.random() <= this.p4) {
            this.leave++;
            this.koc = 0;
        }
        if (this.koa == 0) this.plain1++;
        if (this.kob == 0) this.plain2++;
        if (this.koc == 0) this.plain3++;
    }

    public void serviceChannelsAB() {
        if ((this.koa == 1) & (this.koc == 0) & (Math.random() <= this.p2)) {
            this.koc = 1;
            this.koa = 0;
        }
        if ((this.kob == 1) & (this.koc == 0) & (Math.random() <= this.p3)) {
            this.koc = 1;
            this.kob = 0;
        }
    }

    public void getSmoInfo() {
        System.out.println("Доля обслуженных покупателей = " + (double) this.leave / (this.leave + this.total_refusal));
        System.out.println("Доля отказов = " + (double) this.total_refusal / (this.leave + this.total_refusal));
        System.out.println("Всего обслуженных покупателей: " + this.leave);
        System.out.println("Всего отказов: " + this.total_refusal + "\n");
        System.out.println("Время простоя прилавка А = " + this.plain1 + " секунд");
        System.out.println("Время простоя прилавка В = " + this.plain2 + " секунд");
        System.out.println("Время простоя прилавка С = " + this.plain3 + " секунд\n");
    }

    public void simulationSmo() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 60; j++) {
                this.GetInStream();
                this.transitionServiceChannel();
                this.serviceChannelsAB();
                this.serviceChannelC();
                if (j == 59) {
                    this.total_refusal += this.refusal;
                    System.out.println("Состояние за " + i + " минуту:\n Отказов: " + this.refusal + "\n Очередь: " + this.queue + "\n");
                    this.refusal = 0;
                }
            }
        }
        this.getSmoInfo();
    }
}