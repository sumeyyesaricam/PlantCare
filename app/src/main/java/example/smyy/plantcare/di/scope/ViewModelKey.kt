package example.smyy.plantcare.di.scope

import androidx.lifecycle.ViewModel
import dagger.MapKey
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass


@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)