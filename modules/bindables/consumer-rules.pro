
# Bindables proguard rules #

# noinspection ShrinkerUnresolvedReference

# data binding
-dontwarn androidx.databinding.**
-keep class androidx.databinding.** { *; }
-keep class * extends androidx.databinding.DataBinderMapper
-keep class ** { @androidx.databinding.Bindable <methods>; }
-keep class ** { @androidx.databinding.Bindable <fields>; }

# bindables
-dontwarn com.weltech.bindables.**
-dontwarn androidx.databinding.Bindable
-keep class com.weltech.bindables.** { *; }
-keep class ** extends com.weltech.bindables.**
-keep @androidx.databinding.Bindable interface *