package com.liang.dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerificationCodeDao {
	private int weight = 80; // ��֤��ͼƬ�ĳ��Ϳ�
	private int height = 30;
	private String text; // ����������֤����ı�����
	private Random random = new Random(); // ��ȡ���������
	private String[] fontNames = { "����", "���Ŀ���", "����", "΢���ź�", "����_GB2312" }; // ��������
	private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ"; // ��֤������

	/**
	 * ��ȡ�������ɫ
	 * 
	 * @return Color(r,g,b)
	 */
	private Color randomColor() {
		int r = this.random.nextInt(200); // ����Ϊʲô��150����Ϊ��r��g��b��Ϊ255ʱ����Ϊ��ɫ��Ϊ�˺ñ��ϣ���Ҫ��ɫ��һ�㡣
		int g = this.random.nextInt(200);
		int b = this.random.nextInt(200);
		return new Color(r, g, b); // ����һ�������ɫ
	}

	/**
	 * @param start:
	 * @param end��
	 * @return
	 */
	private Color getColor(int start, int end) {

		int r = start + this.random.nextInt(end - start);
		int g = start + this.random.nextInt(end - start);
		int b = start + this.random.nextInt(end - start);

		return new Color(r, g, b);
	}

	private Font randomFont() // ��ȡ�������
	{
		int index = random.nextInt(fontNames.length); // ��ȡ���������
		String fontName = fontNames[index];
		int style = random.nextInt(4); // �����ȡ�������ʽ��0������ʽ��1�ǼӴ֣�2��б�壬3�ǼӴּ�б��
		int size = random.nextInt(5) + 24; // �����ȡ����Ĵ�С
		return new Font(fontName, style, size); // ����һ�����������
	}

	private char randomChar() // ��ȡ����ַ�
	{
		int index = random.nextInt(codes.length());
		return codes.charAt(index);
	}

	private void drawLine(BufferedImage image) // �������ߣ���֤�������������ֹ���������ͼƬ
	{
		int num = 5; // ��������ߵ�����
		// ��ȡָ���ͼ���ϵ�Graphic����
		Graphics2D g = (Graphics2D) image.getGraphics();
		for (int i = 0; i < num; i++) {
			int x1 = random.nextInt(weight);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(weight);
			int y2 = random.nextInt(height);
			g.setColor(getColor(0, 180));
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private BufferedImage createImage() // ����ͼƬ
	{
		BufferedImage image = new BufferedImage(weight, height, BufferedImage.TYPE_INT_RGB); // ����ͼƬ������
		Graphics2D g = (Graphics2D) image.getGraphics(); // ��ȡ����
		g.setColor(getColor(150, 255)); // ���ñ���ɫ
		g.fillRect(0, 0, weight, height);
		return image; // ����һ��ͼƬ
	}

	public BufferedImage getImage() // ��ȡ��֤��ͼƬ�ķ���
	{
		BufferedImage image = createImage();
		Graphics2D g = (Graphics2D) image.getGraphics(); // ��ȡ����
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) // ���ĸ��ַ�����
		{
			String s = randomChar() + ""; // ��������ַ�����Ϊֻ�л��ַ����ķ�����û�л��ַ��ķ�����������Ҫ���ַ�����ַ����ٻ�
			sb.append(s); // ��ӵ�StringBuilder����
			float x = i * 1.0F * weight / 4; // �����ַ���x����
			g.setFont(randomFont()); // �������壬���
			g.setColor(getColor(0, 180)); // ������ɫ�����
			g.drawString(s, x, height - 5);
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}

	public String getText() // ��ȡ��֤���ı��ķ���
	{
		return text;
	}

	public static void output(BufferedImage image, OutputStream out) throws IOException // ����֤��ͼƬд���ķ���
	{

		ImageIO.write(image, "JPEG", out);
	}
}
