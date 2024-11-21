class ExamChecking extends Thread {
    private int iterations;
    private static int remainingSheets = 500;
    private static final Object lock = new Object();

    public ExamChecking(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 1; i <= iterations; i++) {
            try {
                synchronized (lock) {
                    if (remainingSheets > 0) {
                        remainingSheets -= 50;
                        System.out.println(getName() + " finished checking, at: " + new java.util.Date() + ", exam sheet count is now " + remainingSheets);
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        ExamChecking m = new ExamChecking(6);
        ExamChecking n2 = new ExamChecking(4);
        m.setName("Alibek");
        n2.setName("Dana");
        n2.setPriority(Thread.MAX_PRIORITY);
        m.start();
        n2.start();
    }
}
