# **🔐 Android Password Strength Meter Library**

---
A beautiful, customizable, and easy-to-use Android library that provides real-time password strength visualization with a modern UI design.

---

## ✨ **Features**

- 🎨 Real-Time Visual Strength Indicator - Instant color-coded feedback with 5 bars

- 👁️ Password Visibility Toggle - Eye icon for easy show/hide functionality

- 🛠️ 100% XML-Controlled Customization - No Kotlin code needed in Activity

- 🧮 Smart Multi-Factor Strength Analysis - Evaluates length, case, numbers, special chars

  ---

# **Preview**
---
<p align="center">
  <img src="https://github.com/user-attachments/assets/54e22ab7-86fd-4701-9cef-de2b620beedc"
       alt="Demo GIF"
       width="200">



</p>


## ⚡ **Installation**

**Step 1:** Add JitPack repository to your root build.gradle:

```gradle
maven { url = uri("https://jitpack.io") }
```

**Step 2:** Add the dependency in your app `build.gradle` (example if hosted on JitPack):  

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:ReadMoreTextView:1.0.0'

}
```
## ⚡ **attrs file**

```

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="PasswordStrengthMeter">
        <!-- Reference to the EditText to monitor -->
        <attr name="targetEditText" format="reference" />

        <!-- Show/hide strength text below the bars -->
        <attr name="showStrengthText" format="boolean" />

        <!-- Bar appearance -->
        <attr name="barHeight" format="dimension" />
        <attr name="barCornerRadius" format="dimension" />
        <attr name="barSpacing" format="dimension" />

        <!-- Colors for different strength levels -->
        <attr name="weakColor" format="color" />
        <attr name="fairColor" format="color" />
        <attr name="goodColor" format="color" />
        <attr name="strongColor" format="color" />
        <attr name="veryStrongColor" format="color" />
        <attr name="meterBackgroundColor" format="color" />
    </declare-styleable>
</resources>


```

## ⚡ **Usage**

1. Add in XML

```

   <com.ext.android_password_strengthmeter.PasswordStrengthMeter
        android:id="@+id/passwordStrengthMeter"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:padding="8dp"
        app:targetEditText="@id/passwordEditText"
        app:showStrengthText="true"
        app:barHeight="16dp"
        app:barCornerRadius="8dp"
        app:barSpacing="4dp"
        app:weakColor="#FF3B30"
        app:fairColor="#FF9500"
        app:goodColor="#FFCC00"
        app:strongColor="#34C759"
        app:veryStrongColor="#007AFF"
        app:meterBackgroundColor="#E5E5EA" />
```





## **📄 License**

**MIT License**  
```
Copyright (c) 2025 Excelsior Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED **"AS IS"**, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```



  
