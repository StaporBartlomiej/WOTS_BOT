import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinGDI;

interface GDI32 extends com.sun.jna.platform.win32.GDI32
{
    GDI32 INSTANCE = (GDI32) Native.loadLibrary(GDI32.class);

    boolean BitBlt(WinDef.HDC hdcDest, int nXDest, int nYDest, int nWidth, int nHeight, WinDef.HDC hdcSrc, int nXSrc, int nYSrc, int dwRop);

    WinDef.HDC GetDC(WinDef.HWND hWnd);

    boolean GetDIBits(WinDef.HDC dc, WinDef.HBITMAP bmp, int startScan, int scanLines, byte[] pixels, WinGDI.BITMAPINFO bi, int usage);

    boolean GetDIBits(WinDef.HDC dc, WinDef.HBITMAP bmp, int startScan, int scanLines, short[] pixels, WinGDI.BITMAPINFO bi, int usage);

    boolean GetDIBits(WinDef.HDC dc, WinDef.HBITMAP bmp, int startScan, int scanLines, int[] pixels, WinGDI.BITMAPINFO bi, int usage);

    int SRCCOPY = 0xCC0020;
}