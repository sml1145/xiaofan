package org.webrtc;
/* loaded from: classes5.dex */
class NativeLibrary {
    private static boolean libraryLoaded;
    private static String TAG = "NativeLibrary";
    private static Object lock = new Object();

    NativeLibrary() {
    }

    /* loaded from: classes5.dex */
    static class DefaultLoader implements NativeLibraryLoader {
        @Override // org.webrtc.NativeLibraryLoader
        public boolean load(String name) {
            Logging.d(NativeLibrary.TAG, "Loading library: " + name);
            System.loadLibrary(name);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize(NativeLibraryLoader loader, String libraryName) {
        synchronized (lock) {
            if (libraryLoaded) {
                Logging.d(TAG, "Native library has already been loaded.");
                return;
            }
            Logging.d(TAG, "Loading native library: " + libraryName);
            libraryLoaded = loader.load(libraryName);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLoaded() {
        boolean z;
        synchronized (lock) {
            z = libraryLoaded;
        }
        return z;
    }
}
