# TriangularCustomView
A Custom View to indicate multiple fragments with a triangular indicator.

# Usage

### Step 1: Add it in your root build.gradle (project) at the end of repository:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  

### Step 2: In your app build.gradle add the following dependency
  
  ```
  dependencies {
	        compile 'com.github.Jhuku:TriangularCustomView:0.1.0'
	}
  ```
### Step 3: Set the colour and no of sections in the xml

```
<com.shuvam.triangleindicator.TriangularIndicator
        android:layout_width="match_parent"
        android:layout_height="80dp"
        app:no_of_sections="5"
        android:id="@+id/triangle"
        app:set_colour="#253e97"
        />
```

### Step 4: In your Activity create an array of image resoures

Create an array of image resources from the drawable preferably png icon files.

```
ti = (TriangularIndicator) findViewById(R.id.triangle);

int [] res{ R.drawable.settimerbutton,
						R.drawable.settimerbutton,	
						R.drawable.settimerbutton,
						R.drawable.settimerbutton,
						R.drawable.settimerbutton};

ti.setResources(res);
```


