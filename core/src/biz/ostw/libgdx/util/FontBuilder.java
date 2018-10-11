package biz.ostw.libgdx.util;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontBuilder {

    private final FreeTypeFontGenerator generator;

    private final FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    public FontBuilder(FileHandle fileHandle) {
        this.generator = new FreeTypeFontGenerator(fileHandle);
    }

    public FontBuilder(FreeTypeFontGenerator generator) {
        this.generator = generator;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#size
     */
    public FontBuilder size(int size) {
        this.parameter.size = size;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#characters
     */
    public FontBuilder characters(String characters) {
        this.parameter.characters = characters;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#color
     */
    public FontBuilder color(Color color) {
        this.parameter.color = color;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#mono
     */
    public FontBuilder mono(boolean mono) {
        this.parameter.mono = mono;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#shadowColor
     */
    public FontBuilder shadowColor(Color color) {
        this.parameter.shadowColor = color;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#shadowOffsetX
     */
    public FontBuilder shadowOffsetX(int offset) {
        this.parameter.shadowOffsetX = offset;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#shadowOffsetY
     */
    public FontBuilder shadowOffsetY(int offset) {
        this.parameter.shadowOffsetY = offset;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#borderColor
     */
    public FontBuilder borderColor(Color color) {
        this.parameter.borderColor = color;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#borderGamma
     */
    public FontBuilder borderGamma(float value) {
        this.parameter.borderGamma = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#borderWidth
     */
    public FontBuilder borderWidth(float value) {
        this.parameter.borderWidth = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#borderStraight
     */
    public FontBuilder borderStraight(boolean value) {
        this.parameter.borderStraight = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#flip
     */
    public FontBuilder flip(boolean value) {
        this.parameter.flip = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#gamma
     */
    public FontBuilder gamma(float value) {
        this.parameter.gamma = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#genMipMaps
     */
    public FontBuilder genMipMaps(boolean value) {
        this.parameter.genMipMaps = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#spaceX
     */
    public FontBuilder spaceX(int value) {
        this.parameter.spaceX = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#spaceY
     */
    public FontBuilder spaceY(int value) {
        this.parameter.spaceY = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#kerning
     */
    public FontBuilder kerning(boolean value) {
        this.parameter.kerning = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#incremental
     */
    public FontBuilder incremental(boolean value) {
        this.parameter.incremental = value;
        return this;
    }

    /**
     * @see com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter#hinting
     */
    public FontBuilder hinting(FreeTypeFontGenerator.Hinting value) {
        this.parameter.hinting = value;
        return this;
    }


    public BitmapFont build() {
        BitmapFont bitmapFont = this.generator.generateFont(this.parameter);

        this.generator.dispose();

        return bitmapFont;
    }
}
