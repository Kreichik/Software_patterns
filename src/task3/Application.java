package task3;

import static java.lang.String.*;

interface Answer {
    void say();
}

record VeryInterestingStructure(String answer, long calculationTimeMinutes) {}


class OldMegaComputer {
    public VeryInterestingStructure giveFinalAnswer() {

        long duration = 5000000;
        return new VeryInterestingStructure("42", duration);
    }
}


class ComputerAdapter implements Answer {
    private OldMegaComputer oldMegaComputer = new OldMegaComputer();

    @Override
    public void say() {
        VeryInterestingStructure data = oldMegaComputer.giveFinalAnswer();

        String realAnswer = data.answer();
        double timeCalculate = data.calculationTimeMinutes() / 1000000;

        String formattedResult = format("%s, Dumal %.0f millionov let", realAnswer, timeCalculate);

        System.out.println(formattedResult);
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