package biz.ostw.game.spermotron;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Locale;

public class SkinFactory {

    private static final FileFilter FILE_FILTER_FNT = new FileFilter() {

        @Override
        public boolean accept(File file) {

            return file.isFile() && file.getName().toLowerCase().endsWith(".fnt");
        }
    };

    public static BitmapFont[] bitmapFonts(Locale locale) {

        final BitmapFont[] result;
        final String path = "fonts/" + (locale != null ? locale.getCountry() : "default");
        final FileHandle fileHandle = Gdx.files.internal(path);

        if (fileHandle.exists()) {

            if (fileHandle.isDirectory()) {

                throw new IllegalStateException("'" + path + "' is a directory!");
            } else {

                FileHandle[] files = fileHandle.list(FILE_FILTER_FNT);
                result = new BitmapFont[files.length];

                for (int i = 0; i < files.length; i++) {
                    result[i] = new BitmapFont(files[i]);
                }
            }
        } else {
            result = SkinFactory.bitmapFonts(null);
        }

        return result;
    }
}
