from PIL import Image

# https://pillow.readthedocs.io/en/5.1.x/reference/Image.html

class PNGSplit():
    def __init__(self):
        print("hi")
        get_image("pan.png")

    def get_image(image_path):
        """Get a numpy array of an image so that one can access values[x][y]."""
        image = Image.open(image_path, 'r')
        width, height = image.size
        pixel_values = list(image.getdata())
        if image.mode == 'RGBA':
            channels = 4
        elif image.mode == 'L':
            channels = 1
        else:
            print("Unknown mode: %s" % image.mode)
            return None
        pixel_values = numpy.array(pixel_values).reshape((width, height, channels))
        return pixel_values

PNGSplit()