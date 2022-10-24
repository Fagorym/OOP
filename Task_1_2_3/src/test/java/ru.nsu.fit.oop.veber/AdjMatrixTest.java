package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.BeforeEach;

public class AdjMatrixTest<T> {
    AdjMatrix<T> testMatrix;

    @BeforeEach
    public void initMatrix(){
        var testMatrix = new AdjMatrix<>();
    }


}
