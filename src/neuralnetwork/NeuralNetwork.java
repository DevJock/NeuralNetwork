package neuralnetwork;

public class NeuralNetwork{

    private int _inputConfig;
    private int[] _hiddenConfig;
    private int _outputConfig;

    private Matrix _wih;
    private Matrix _who;
    private Matrix _biasH;
    private Matrix _biasO;

    private double _learningRate;

    public NeuralNetwork(int inputConfig, int[] hiddenConfig, int outputConfig, double learningRate){
        _inputConfig = inputConfig;
        _hiddenConfig = new int[]{hiddenConfig[0]};
        _outputConfig = outputConfig;
        _learningRate = learningRate;
        _wih = new Matrix(_hiddenConfig[0],_inputConfig);
        _who = new Matrix(_outputConfig,_hiddenConfig[0]);
        _wih.Randomize();
        _who.Randomize();
        _biasH = new Matrix(_hiddenConfig[0],2);
        _biasO = new Matrix(_outputConfig,2);
        _biasH.Randomize();
        _biasO.Randomize();
    }

    public double[] Predict(double[][] inputs){
        Matrix inp = new Matrix(inputs);
        Matrix hidden = Matrix.CrossProduct(_wih,inp);
        hidden.Add(_biasH);
        hidden = ActivationFunction.ApplyActivation(hidden,0);

        Matrix output = Matrix.CrossProduct(_who,hidden);
        output.Add(_biasO);
        output = ActivationFunction.ApplyActivation(output,0);
        System.out.println(output);
        return output.toArray();
    }

    public void Train(double[][] inputs, double[][] expected){
        Matrix inp = new Matrix(inputs);
        Matrix hidden = Matrix.CrossProduct(_wih,inp);
        hidden.Add(_biasH);
        hidden = ActivationFunction.ApplyActivation(hidden,0);

        Matrix output = Matrix.CrossProduct(_who,hidden);
        output.Add(_biasO);
        output = ActivationFunction.ApplyActivation(output,0);

        Matrix target = new Matrix(expected);
        Matrix errors = Matrix.Subtract(output,target);
        Matrix gradients = ActivationFunction.ApplyActivation(errors, 1);
        gradients = Matrix.CrossProduct(gradients, errors);
        gradients.DotProduct(_learningRate);

        Matrix hiddenT = Matrix.Transpose(hidden);
        Matrix who_deltas = Matrix.CrossProduct(gradients, hiddenT);
        _who.Add(who_deltas);
        _biasO.Add(gradients);

        Matrix whoT = Matrix.Transpose(_who);
        Matrix hiddenErrors = Matrix.CrossProduct(whoT, errors);

        Matrix hiddenGradient = ActivationFunction.ApplyActivation(hidden,1);
        hiddenGradient = Matrix.CrossProduct(hiddenGradient, hiddenErrors);
        hiddenGradient.DotProduct(_learningRate);

         // Calcuate input->hidden deltas
        Matrix inputsT = Matrix.Transpose(inp);
        Matrix wih_deltas = Matrix.CrossProduct(hiddenGradient, inputsT);

        _wih.Add(wih_deltas);
        // Adjust the bias by its deltas (which is just the gradients)
        _biasH.Add(hiddenGradient);
    }


    @Override
    public String toString(){
        String str = "Inputs: "+_inputConfig+" Hidden: [";
        for(int i=0;i<_hiddenConfig.length - 1;i++){
            str += _hiddenConfig[i]+",";
        }
        str += _hiddenConfig[_hiddenConfig.length - 1];
        str = str.trim();
        str += "] Outputs: "+_outputConfig;
        return str;
    }
}