# NinePatchChunk

[![](https://jitpack.io/v/YvesCheung/NinePatchChunk.svg)](https://jitpack.io/#YvesCheung/NinePatchChunk)

- Convert a BitmapDrawable into NinePatchDrawable at runtime.
- Just like iOS `ResizableImageWithCapInsets` method.

| Before | After | 
| :------: | :------: |
| ![](https://raw.githubusercontent.com/YvesCheung/NinePatchChunk/main/Art/bubble.jpg)     | ![](https://raw.githubusercontent.com/YvesCheung/NinePatchChunk/main/Art/BubbleAfterNinePatch.jpg) |

## Usage

* Kotlin

```kotlin
//Stretch from the middle
val capInsets = Rect(bitmapWidth / 2, bitmapHeight / 2, bitmapWidth / 2, bitmapHeight / 2)
val drawable = bitmapDrawable.resizableImageWithCapInsets(capInsets)
imageView.setImageDrawable(drawable)

```

* Java

```java
//Stretch from the middle
Rect capInsets = new Rect(bitmapWidth / 2, bitmapHeight / 2, bitmapWidth / 2, bitmapHeight / 2);
NinePatchDrawable drawable = NinePatchGenerator.resizableImageWithCapInsets(bitmapDrawable, capInsets);
imageView.setImageDrawable(drawable);
```

## Install

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.YvesCheung:NinePatchChunk:x.y.z'
}
```

[![](https://jitpack.io/v/YvesCheung/NinePatchChunk.svg)](https://jitpack.io/#YvesCheung/NinePatchChunk)

## Reference
Fork from [https://github.com/Anatolii/NinePatchChunk](https://github.com/Anatolii/NinePatchChunk)

