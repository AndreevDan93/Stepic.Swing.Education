import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame {
    static Image bomb;
    static Window window = new Window();
    static long frameTime = System.nanoTime();
    static float bombX = 0;
    static float bombY = 0;
    static float bombV = 540;
    static boolean left;
    static boolean right;

    public static void main(String[] args) throws IOException {

        initFrame();
        bomb = ImageIO.read(Window.class.getResourceAsStream("icon.png"));
        GameField gameField = new GameField();
        window.add(gameField);
        window.setVisible(true);


    }


    static void initFrame() {
        window.pack();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        window.setBounds(0, 0, dimension.width, dimension.height);


    }

    private static void move(Graphics g) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - frameTime) * 0.000000001f;
        frameTime = currentTime;


        if (bombX <= 0) {
            right = true;
            left = false;
        } else if (bombX >= dimension.width - bomb.getWidth(null) / 3) {
            right = false;
            left = true;
        }


        if (bombX <= dimension.width / 4 - bomb.getWidth(null) && right) {

            bombX = (float) (bombX + 0.25 * bombV * deltaTime);
            bombY = (bombY + bombV * deltaTime);
            g.drawImage(bomb, (int) bombX, (int) bombY, null);
        }
        if (bombX >= dimension.width / 4 - bomb.getWidth(null) &&
                bombX <= dimension.width * 3 / 4 - bomb.getWidth(null) && right) {

            bombX = (bombX + bombV * deltaTime);
            bombY = (bombY - bombV * deltaTime);
            g.drawImage(bomb, (int) bombX, (int) bombY, null);
        }

        if (bombX >= dimension.width * 3 / 4 - bomb.getWidth(null) &&
                bombX <= dimension.width - bomb.getWidth(null) &&
                right) {

            bombX = (float) (bombX + 0.75 * bombV * deltaTime);
            bombY = (float) (bombY +  bombV * deltaTime);
            g.drawImage(bomb, (int) bombX, (int) bombY, null);
        }

        if (bombX >= dimension.width * 3 / 4 - bomb.getWidth(null) && left) {

            bombX = (bombX - bombV * deltaTime);
            bombY = (bombY + bombV * deltaTime);
            g.drawImage(bomb, (int) bombX, (int) bombY, null);
        }
        if (bombX <= dimension.width * 3 / 4 - bomb.getWidth(null) && left) {

            bombX = (bombX - bombV * deltaTime);
            bombY = (float) (bombY - 0.6 * bombV * deltaTime);
            g.drawImage(bomb, (int) bombX, (int) bombY, null);
        }



















           /* if (bombY < dimension.height - bomb.getHeight(null) && bombX <= 0) {
                bombY = (bombY + bombV * deltaTime);
                g.drawImage(bomb, (int) bombX, (int) bombY, null);
            } else if (bombY >= dimension.height - bomb.getHeight(null) && bombX < dimension.width - bomb.getWidth(null)) {
                bombX = (bombX + bombV * deltaTime);
                g.drawImage(bomb, (int) bombX, (int) bombY, null);
            } else if (bombY > 0 && bombX > dimension.width - bomb.getWidth(null)) {
                bombY = (bombY - bombV * deltaTime);
                g.drawImage(bomb, (int) bombX, (int) bombY, null);
            } else if (bombX > 0 && bombY <= 0) {
                bombX = (bombX - bombV * deltaTime);
                g.drawImage(bomb, (int) bombX, (int) bombY, null);
            }
         bombX =  (bombX + bombV*deltaTime);
        bombY =  (bombY + bombV*deltaTime / 2);
        g.drawImage(bomb,(int)bombX,(int)bombY,null);
        if (bombX > dimension.width || bombY > dimension.height){
            bombX = -250;
            bombY = -250;
        }*/


    }


    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Window.move(g);
            repaint();


        }
    }
}