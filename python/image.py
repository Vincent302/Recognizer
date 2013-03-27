

import Image
import ImageDraw
import os

for i in range(0,37):
	temp_img = Image.open(str(i) + ".png")

	temp_img.save(str(i) + "_0.png")

	new_img = temp_img.rotate(5)
	draw = ImageDraw.Draw(new_img)
	draw.line(((0,0),(11,0)),fill=(255,255,255))
	draw.line(((23,0),(23,12)),fill=(255,255,255))
	draw.line(((0,25),(0,47)),fill=(255,255,255))
	draw.line(((1,36),(1,47)),fill=(255,255,255))
	new_img.save(str(i) + "_1.png")

	temp_img = Image.open(str(i) + ".png")
	new_img = temp_img.rotate(-5)
	draw = ImageDraw.Draw(new_img)
	draw.line(((13,0),(23,0)),fill=(255,255,255))
	draw.line(((23,36),(23,47)),fill=(255,255,255))
	draw.line(((0,0),(0,23)),fill=(255,255,255))
	draw.line(((1,0),(1,12)),fill=(255,255,255))
	new_img.save(str(i) + "_2.png")

	os.remove(str(i) + ".png")












































