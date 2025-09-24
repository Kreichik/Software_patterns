package task3;

interface Answer {
    void say();
}

class OldMegaComputer {
    public int giveAnswer() {

        return 42;
    }
}

class ComputerAdapter implements Answer {
    private OldMegaComputer oldMegaComputer = new OldMegaComputer();

    @Override
    public void say() {
        int result = oldMegaComputer.giveAnswer();
        System.out.println(result);
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