package neuralnetwork;
public class Matrix {
    private int _rows;
    private int _columns;

    private double[][] _values;

    public int GetRows() {
        return _rows;
    }

    public int GetColumns() {
        return _columns;
    }

    public double[][] GetValues() {
        return _values;
    }

    public double GetValue(int row, int column) {
        return _values[row][column];
    }

    public void SetValue(int row, int column, double value) {
        _values[row][column] = value;
    }

    public Matrix(int rows, int columns) {
        _rows = rows;
        _columns = columns;
        _values = new double[_rows][_columns];
    }

    public Matrix(int rows, int columns, double min, double max) {
        _rows = rows;
        _columns = columns;
        _values = new double[_rows][_columns];
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                _values[i][j] = RandomValues(min, max);
            }
        }
    }

    public Matrix(double[] array) {
        _rows = array.length / 2;
        _columns = array.length / 2;
        _values = new double[_rows][_columns];
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                _values[i][j] = array[i * 4 + j];
            }
        }
    }

    public Matrix(double[][] array) {
        _rows = array.length;
        _columns = array[0].length;
        _values = new double[_rows][_columns];
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                _values[i][j] = array[i][j];
            }
        }
    }

    public void Randomize() {
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                _values[i][j] = RandomValues(-1, 1);
            }
        }
    }

    public static Matrix Transpose(Matrix m) {
        Matrix temp = new Matrix(m.GetColumns(), m.GetRows());
        for (int i = 0; i < temp.GetColumns(); i++) {
            for (int j = 0; j < temp.GetRows(); j++) {
                temp.SetValue(j, i, m.GetValue(i, j));
            }
        }
        return temp;
    }

    public void Randomize(double min, double max) {
        for (int i = 0; i < _columns; i++) {
            for (int j = 0; j < _rows; j++) {
                _values[i][j] = RandomValues(min, max);
            }
        }
    }

    public void Add(Matrix matrix) {
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                _values[i][j] += matrix.GetValue(i, j);
            }
        }
    }

    public static Matrix Subtract(Matrix a, Matrix b) {
        Matrix o = new Matrix(a.GetRows(), a.GetColumns());
        for (int i = 0; i < a.GetRows(); i++) {
            for (int j = 0; j < a.GetColumns(); j++) {
                o.SetValue(i, j, a.GetValue(i, j) - b.GetValue(i, j));
            }
        }
        return o;
    }

    public void DotProduct(double value) {
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                _values[i][j] *= value;
            }
        }
    }

    public static Matrix CrossProduct(Matrix a, Matrix b) {
        Matrix c = new Matrix(a.GetRows(), b.GetColumns());
        for (int i = 0; i < a.GetRows(); i++) {
            for (int j = 0; j < b.GetColumns(); j++) {
                for (int k = 0; k < a.GetColumns(); k++) {
                    c.SetValue(i, j, c.GetValue(i, j) + a.GetValue(i, k) * b.GetValue(k, j));
                }
            }
        }
        return c;
    }

    public double[] toArray() {
        double[] arr = new double[_rows * _columns];
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                arr[i + j] = _values[i][j];
            }
        }
        return arr;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _columns; j++) {
                str += _values[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }

    private double RandomValues(double min, double max) {
        return Math.random() * max + min;
    }
}