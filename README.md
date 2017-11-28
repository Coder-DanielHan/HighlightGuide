# HighlightGuide [![Platform](https://img.shields.io/badge/platform-android-brightgreen.svg?style=flat)](http://developer.android.com/index.html) <img src="https://img.shields.io/badge/license-Apache 2.0-brightgreen.svg?style=flat"> [![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)

HighlightGuide is an Android library used to guide user how to do the next step.

![Demo](gif/demo.gif)

## Features
- Background color
- Circle , Oval and Rounded Rectangle focus shapes
- Custom decor view
- All and Next mode

## Usage
```java
mGuide = new GuideBuilder(MainActivity.this)
        .isInterceptClick(true)
        .isClickDismiss(false)
        .setEnableNext(true)
        .setBgColorResId(R.color.highlight)
        .addItem(new Item(new CircleHighlightShape(40), R.id.btn_rightLight, R.layout.info_gravity_left_down, Item.ANCHOR_LEFT, Item.FIT_END, -5, 0, null))
        .addItem(new Item(new RectHighlightShape(40, 10, 10), R.id.btn_light, R.layout.info_gravity_left_down, Item.ANCHOR_RIGHT, Item.FIT_START, 5, 0, null))
        .build();
mGuide.setOnClickListener(new HighlightGuideInterface.OnClickListener() {
    @Override
    public void onClick() {
        Toast.makeText(MainActivity.this, "clicked and show next tip view by yourself", Toast.LENGTH_SHORT).show();
        mGuide.next();
    }
});
mGuide.show(MainActivity.this);
```


License
=======

    Copyright 2017 Faruk Toptaş

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






