
import Image
import ImageDraw


for i in range(0,37):
	blank = Image.open("blank.png")
	blank.save(str(i) + "_3.png")
	blank.save(str(i) + "_4.png")
	blank.save(str(i) + "_5.png")
	blank.save(str(i) + "_6.png")


	temp = Image.open(str(i) + "_0.png")


	region = temp.crop((0,0,21,48))
	new_img = Image.open(str(i) + "_3.png")
	new_img.paste(region,(3,0,24,48))
	new_img.save(str(i) + "_3.png")

	region = temp.crop((3,0,24,48))
	new_img = Image.open(str(i) + "_4.png")
	new_img.paste(region,(0,0,21,48))
	new_img.save(str(i) + "_4.png")

	region = temp.crop((0,0,24,45))
	new_img = Image.open(str(i) + "_5.png")
	new_img.paste(region,(0,3,24,48))
	new_img.save(str(i) + "_5.png")

	region = temp.crop((0,3,24,48))
	new_img = Image.open(str(i) + "_6.png")
	new_img.paste(region,(0,0,24,45))
	new_img.save(str(i) + "_6.png")





































