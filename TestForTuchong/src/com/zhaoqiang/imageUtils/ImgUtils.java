package com.zhaoqiang.imageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgUtils {

	/**
	 * removeBackground方法去除验证码噪点，首先我定义了一个临界阈值，这个值代表像素点的亮度，
	 * 我们在实际扫描验证码的每一个像素块时通过判断该像素块的亮度（获取该像素块的三原色）是否超过该自定义值，
	 * 从而判断出是否删除或保留该像素块，因此针对不同的验证码我们可以调整这个阈值，
	 * 比如像我上面列出的几种验证码波动一般都在100~600之间，通过测试就得出一个比较适中的阈值可以大大提高验证码的提纯度。
	 */
	public static void removeBackground(String imgUrl, String resUrl) {
		// 定义一个临界阈值
		int threshold = 300;
		try {
			BufferedImage img = ImageIO.read(new File(imgUrl));
			int width = img.getWidth();
			int height = img.getHeight();
			for (int i = 1; i < width; i++) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						Color color = new Color(img.getRGB(x, y));
						System.out.println("red:" + color.getRed() + " | green:" + color.getGreen() + " | blue:"
								+ color.getBlue());
						int num = color.getRed() + color.getGreen() + color.getBlue();
						if (num >= threshold) {
							img.setRGB(x, y, Color.WHITE.getRGB());
						}
					}
				}
			}
			for (int i = 1; i < width; i++) {
				Color color1 = new Color(img.getRGB(i, 1));
				int num1 = color1.getRed() + color1.getGreen() + color1.getBlue();
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						Color color = new Color(img.getRGB(x, y));

						int num = color.getRed() + color.getGreen() + color.getBlue();
						if (num == num1) {
							img.setRGB(x, y, Color.BLACK.getRGB());
						} else {
							img.setRGB(x, y, Color.WHITE.getRGB());
						}
					}
				}
			}
			File file = new File(resUrl);
			if (!file.exists()) {
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			ImageIO.write(img, "jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这样就能得到一个降噪之后的验证码，再后续通过颜色反转将验证码的内容显示出来就完成了干扰像素的去除工作，
	 * 接下来非必要的一项工作就是将验证码进行裁边。为什么要裁边呢，通过观察我们发现验证码的边角部分没有干扰素涂抹，
	 * 在我们的去除干扰素过程中可能会影响到这部分像素块导致最后边角有噪点，这时将边角裁掉几个像素就可以了。
	 */
	public static void cuttingImg(String imgUrl) {
		try {
			File newfile = new File(imgUrl);
			BufferedImage bufferedimage = ImageIO.read(newfile);
			int width = bufferedimage.getWidth();
			int height = bufferedimage.getHeight();
			if (width > 52) {
				bufferedimage = ImgUtils.cropImage(bufferedimage, (int) ((width - 52) / 2), 0,
						(int) (width - (width - 52) / 2), (int) (height));
				if (height > 16) {
					bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((height - 16) / 2), 52,
							(int) (height - (height - 16) / 2));
				}
			} else {
				if (height > 16) {
					bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((height - 16) / 2), (int) (width),
							(int) (height - (height - 16) / 2));
				}
			}
			ImageIO.write(bufferedimage, "jpg", new File(imgUrl));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		if (startX == -1) {
			startX = 0;
		}
		if (startY == -1) {
			startY = 0;
		}
		if (endX == -1) {
			endX = width - 1;
		}
		if (endY == -1) {
			endY = height - 1;
		}
		BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
		for (int x = startX; x < endX; ++x) {
			for (int y = startY; y < endY; ++y) {
				int rgb = bufferedImage.getRGB(x, y);
				result.setRGB(x - startX, y - startY, rgb);
			}
		}
		return result;
	}
}
