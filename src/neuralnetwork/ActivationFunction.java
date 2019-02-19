package neuralnetwork;
public class ActivationFunction {

    public static double Sigmoid(double x) {
        return 1 / (1 + Math.exp(x));
    }

    public static double dSigmoid(double x){
        return x * (1 - x);
    }

    public static Matrix ApplyActivation(Matrix mat, int functionID){
        for(int i=0;i<mat.GetRows();i++){
            for(int j=0;j<mat.GetColumns();j++){
                double val = 0;
                switch(functionID){
                    case 0:val = Sigmoid(mat.GetValue(i,j));break;
                    case 1:val = dSigmoid(mat.GetValue(i,j));break;
                }
                mat.SetValue(i, j, val);
            }
        }
        return mat;
    }

}