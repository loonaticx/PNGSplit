import shutil

import subprocess
from direct.showbase.ShowBase import ShowBase
from direct.directbase import DirectStart
from direct.showbase.Loader import Loader
from glob import glob
import os.path
from panda3d.core import Filename

directories = {
                "phase_3/maps"
              }

FNULL = open(os.devnull, 'w')

def clean():
    if fileExtension == ".png":
        #Hacky because we're still on Python 2.7. If we upgraded, we would be able to use "pathlib" (Python 3.4)
        #Also, I'm using the std args to suppress output since it's kind of redundant. Now I see why Python 2.7 isn't the best.
        subprocess.call("move %s %s" % (baseName +".png", directory + "/png-output/"), stdout=FNULL, stderr=subprocess.STDOUT, shell=True)
        subprocess.call("del %s" % (baseName +".png"), stdout=FNULL, stderr=subprocess.STDOUT, shell=True)
        #This... KINDA works..? Needs to be ran a few times doe :c

        #shutil.move(baseName +".png", directory + "/png-output/")


for directory in directories:
    files = glob(directory + "/*")
    if not os.path.exists(directory + "/png-output"):
        os.mkdir(directory + "/png-output")

    for file in files:
        baseName = os.path.splitext(file)[0]
        fileExtension = os.path.splitext(file)[1]

        if fileExtension == ".jpg":
            rgbFileName = baseName + "_a.rgb"
            rgbFile = Filename(rgbFileName)
            
            if rgbFile.exists():
                tex = loader.loadTexture(file, rgbFile)
                tex.write(baseName + ".png")
               
            else:
                tex = loader.loadTexture(file)
                tex.write(baseName + ".png")
        
        if fileExtension ==".rgb":
            jpgFileName = file.split('_a.')
            jpgFile = Filename(jpgFileName[0] + ".jpg")
            if not jpgFile.exists():
                tex = loader.loadTexture(file)
                tex.write(baseName + ".png")

        clean()
