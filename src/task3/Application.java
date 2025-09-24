package task3;

interface Answer {
    void say();
}

class OldMegaComputer {
    public void giveAnswer() {
        System.out.println("42");
    }
}

class ComputerAdapter implements Answer {
    private OldMegaComputer oldMegaComputer = new OldMegaComputer();

    @Override
    public void say() {
        oldMegaComputer.giveAnswer();
    }
}

public class Application {
    public static void clientCode(Answer answer) {
        System.out.println("Otvet na glavnyi vopros o zhizni, vselenoy i vsego takogo: \n");
        answer.say();
    }

    public static void main(String[] args) {
        ComputerAdapter adapter = new ComputerAdapter();
        clientCode(adapter);
    }
}