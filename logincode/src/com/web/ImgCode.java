package com.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/getcode")
public class ImgCode extends HttpServlet {

	
	// ͼ����֤����ַ�����ϵͳ�����������ַ�����ѡ��һЩ�ַ���Ϊ��֤��

    private static String codeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// �����֤�뼯�ϵĳ���
        int charsLength = codeChars.length();
        // ����3����¼�ǹرտͻ���������Ļ�����

        // ��3����䶼���Թر�������Ļ���������������������İ汾��ͬ������3������֧��Ҳ��ͬ

        // ��ˣ�Ϊ�˱��������ͬʱʹ����3��������ر�������Ļ�����
        response.setHeader("ragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // ����ͼ����֤��ĳ��Ϳ�
        int width = 120, height = 50;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        
        Random random = new Random();
        //������ɫ
        g.setColor(getRandomColor(180, 250));
        g.fillRect(0, 0, width, height);

        //����
        g.setFont(new Font("Times New Roman", Font.ITALIC, height));
         //������ɫ
        g.setColor(getRandomColor(120, 180));
        // �û��������������ɵ���֤��
        StringBuffer validationCode = new StringBuffer();
        // ��֤����������
        //String[] fontNames = { "Times New Roman", "Book antiqua", "Arial" };

        // �������4����֤��
        for (int i = 0; i < 4; i++) {
            // ������õ�ǰ��֤����ַ�������
            //g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));
            // �����õ�ǰ��֤����ַ�
            char codeChar = codeChars.charAt(random.nextInt(charsLength));
            
            validationCode.append(codeChar);
            // ������õ�ǰ��֤���ַ�����ɫ
            g.setColor(getRandomColor(10, 100));
            // ��ͼ���������֤���ַ���x��y����������ɵ�
            g.drawString(codeChar+"", 28 * i + random.nextInt(4),
                    height - random.nextInt(6));
        }

        // ���HttpSession����

        HttpSession session = request.getSession();

        // ����session����5����ʧЧ

        session.setMaxInactiveInterval(5 * 60);

        // ����֤�뱣����session������,keyΪvalidation_code

        session.setAttribute("validation_code", validationCode.toString());
         //�ر�Graphics����

        g.dispose();

        OutputStream outS = response.getOutputStream();

        ImageIO.write(image, "JPEG", outS);
	}
	
	// rgb(255,255,255)
	  private Color getRandomColor(int minColor, int maxColor) {
          Random random = new Random();
            if(minColor > 255){
                minColor = 255;
            }
            if(maxColor > 255){
                maxColor = 255;
            }
            //���r�������ɫֵ
            int red = minColor+random.nextInt(maxColor-minColor);
            int green = minColor + random.nextInt(maxColor-minColor);
            int blue = minColor + random.nextInt(maxColor-minColor);
            return new Color(red,green,blue);
    }


}
