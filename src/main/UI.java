package main;

import object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font consolasFont;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;

        consolasFont = new Font("Consolas", Font.PLAIN, 40);
        KeyObject key = new KeyObject(gp);
        keyImage = key.image;

    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if (gameFinished == true){

            g2.setFont(consolasFont);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treassure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is: " + decimalFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            gp.gameThread = null;


        }
        else {

            g2.setFont(consolasFont);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("Keys: " + gp.player.hasKey, 74, 60);

            // TRACK GAME TIME
            playTime +=(double)1/60;
            g2.drawString("Time: " + decimalFormat.format(playTime), gp.tileSize*11, 65);

            if (messageOn == true){

                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if (messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }


    }
}
