/*
 * Copyright (C) 2015 The IDigiSign Android Project
 *
 * Licensed under the Deepnet Security (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.deepnetsecurity.com/legal/
 *
 * Under the international copyright law, neither the Deepnet
 * Security software or documentation may be copied, reproduced,
 * translated or reduced to any electronic medium or machine
 * readable form, in whole or in part, without the prior written
 * consent of Deepnet Security.
 *
 * Please read your licence agreement with Deepnet carefully
 * and make sure you understand the exact terms of usage.
 * In particular, for which projects, on which platforms and
 * at which sites, you are allowed to use the product.
 * You are not allowed to make any modifications to the
 * product. If you feel the need for any modifications, please
 * contact Deepnet Security.
 */

package uk.co.emx2.okhttp_demo;

/**
 * Create by
 * Name: Xin Meng
 * Project: idigisign-android
 * Package: com.idigisign.android.util
 * Date: 19/10/15
 * Time: 16:10
 * Version: V 1.0
 */

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Provides a simplified way to show toast messages without having to create the toast, set the
 * desired gravity, etc.
 */
public class ToastUtils {
    public enum Duration {SHORT, LONG}

    private ToastUtils() {
        throw new AssertionError();
    }

    public static Toast showToast(Context context, int stringResId) {
        return showToast(context, stringResId, Duration.SHORT);
    }

    public static Toast showToast(Context context, int stringResId, Duration duration) {
        return showToast(context, context.getString(stringResId), duration);
    }

    public static Toast showToast(Context context, String text) {
        return showToast(context, text, Duration.SHORT);
    }

    public static Toast showToast(Context context, String text, Duration duration) {
        Toast toast = Toast.makeText(context, text,
                (duration == Duration.SHORT ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;
    }
}
