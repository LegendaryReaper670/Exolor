package org.fallenreaper.nebula2d.renderer_manager.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;

public class GLUtil {

    public static void _clearDepth(double pDepth) {
        
        GL11.glClearDepth(pDepth);
    }

    public static void _clearColor(float pRed, float pGreen, float pBlue, float pAlpha) {
        
        GL11.glClearColor(pRed, pGreen, pBlue, pAlpha);
    }

    public static void _clearStencil(int pIndex) {
      
        GL11.glClearStencil(pIndex);
    }

    public static void _clear(int pMask, boolean pCheckError) {
        
        GL11.glClear(pMask);
        if (pCheckError) {
            _getError();
        }

    }

    public static void _glDrawPixels(int pWidth, int pHeight, int pFormat, int pType, long pPixels) {
       
        GL11.glDrawPixels(pWidth, pHeight, pFormat, pType, pPixels);
    }

    public static void _vertexAttribPointer(int pIndex, int pSize, int pType, boolean pNormalized, int pStride, long pPointer) {
        
        GL20.glVertexAttribPointer(pIndex, pSize, pType, pNormalized, pStride, pPointer);
    }

    public static void _vertexAttribIPointer(int pIndex, int pSize, int pType, int pStride, long pPointer) {
        
        GL30.glVertexAttribIPointer(pIndex, pSize, pType, pStride, pPointer);
    }

    public static void _enableVertexAttribArray(int pIndex) {
        
        GL20.glEnableVertexAttribArray(pIndex);
    }

    public static void _disableVertexAttribArray(int pIndex) {
        
        GL20.glDisableVertexAttribArray(pIndex);
    }

    public static void _drawElements(int pMode, int pCount, int pType, long pIndices) {
        
        GL11.glDrawElements(pMode, pCount, pType, pIndices);
    }




    public static void _pixelStore(int pParameterName, int pParam) {
        
        GL11.glPixelStorei(pParameterName, pParam);
    }

    public static void _readPixels(int pX, int pY, int pWidth, int pHeight, int pFormat, int pType, ByteBuffer pPixels) {
      
        GL11.glReadPixels(pX, pY, pWidth, pHeight, pFormat, pType, pPixels);
    }

    public static void _readPixels(int pX, int pY, int pWidth, int pHeight, int pFormat, int pType, long pPixels) {
     
        GL11.glReadPixels(pX, pY, pWidth, pHeight, pFormat, pType, pPixels);
    }

    public static int _getError() {
        
        return GL11.glGetError();
    }

    public static String _getString(int pName) {
        
        return GL11.glGetString(pName);
    }

    public static int _getInteger(int pPname) {
       
        return GL11.glGetInteger(pPname);
    }
}
