package visitor;

import transport.Auto;
import transport.Motorcycle;

public interface Visitor {
    void visit(Auto auto);
    void visit(Motorcycle motorcycle);
}