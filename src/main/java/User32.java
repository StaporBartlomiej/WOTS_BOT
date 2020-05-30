import com.sun.jna.Native;

interface User32 extends com.sun.jna.platform.win32.User32
{
    User32 INSTANCE = (User32) Native.loadLibrary(User32.class);

    HWND GetDesktopWindow();
}