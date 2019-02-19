/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

/**
 *
 * @author Chiraag Bangera
 */
public class MainClass {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2,new int[]{2},1,0.2);
        nn.Train(new double[][]{{0,0},{0,1},{1,0},{1,1}}, new double[][]{{0},{1},{1},{0}});
        nn.Predict(new double[][]{{1,1}});
    }
}
