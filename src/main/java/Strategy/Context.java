package Strategy;

import Models.User;

public class Context {
    private StrategyInterface strategyInterface;

    public Context(StrategyInterface strategyInterface){
        this.strategyInterface =strategyInterface;
    }

    public String executeStrategy(User user){
        return strategyInterface.toString();
    }
}
