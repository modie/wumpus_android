package com.gna.moody.wumpus;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
 
public class WumpusPit extends Cell {
 
    public WumpusPit(int x, int y) {
        super(x, y);
    }
 
    public void draw(Canvas g, Resources res, int x, int y, int w, int h) {
    Bitmap im = BitmapFactory.decodeResource(res, R.drawable.roompit);
    g.drawBitmap(im, null, new Rect(x*w, y*h, (x*w)+w, (y*h)+h), new Paint());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Empty) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "PIT";
    }

	@Override
	public int Drawable() {
		
		return 0;
	}
}
