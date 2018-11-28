package biz.ostw.game.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.File;
import java.io.FileFilter;
import java.util.Locale;

public class BaseSkinFactory implements SkinFactory {

    private static final String ATLAS_FILE_NAME = "pack.atlas";

    private static final String SKIN_FILE_NAME = "skin.skin";

    private static final FileFilter FILE_FILTER_FNT = new FileFilter() {

        @Override
        public boolean accept(File file) {

            return file.isFile() && file.getName().toLowerCase().endsWith(".fnt");
        }
    };

    @Override
    public Skin get(Locale locale) {

        Skin skin;
        final String path = "skins/" + (locale != null ? locale.getCountry() : "default") + "/";
        final FileHandle baseHandle = Gdx.files.internal(path);

        try {

            checkDirectory(baseHandle);

            final FileHandle skinHandle = baseHandle.child(SKIN_FILE_NAME);
            final FileHandle atlasHandle = baseHandle.child(ATLAS_FILE_NAME);

            try {

                checkFile(skinHandle);
                checkFile(atlasHandle);

                TextureAtlas atlas = new TextureAtlas(atlasHandle);
                skin = new Skin(skinHandle, atlas);
            } catch (Exception e) {

                skin = this.get(null);
            }
        } catch (Exception t) {

            skin = this.get(null);
        }

        return skin;
    }

    private static final void checkFile(FileHandle handle) {

        if (handle.exists()) {

            if (handle.isDirectory()) {
                throw new IllegalStateException("'" + handle.path() + "' is a directory!");
            }
        } else {

            throw new IllegalStateException("'" + handle.path() + "' not exists!");
        }
    }

    private static final void checkDirectory(FileHandle handle) {

        if (handle.exists()) {

            if (!handle.isDirectory()) {
                throw new IllegalStateException("'" + handle.path() + "' is not a directory!");
            }
        } else {

            throw new IllegalStateException("'" + handle.path() + "' not exists!");
        }
    }
}
