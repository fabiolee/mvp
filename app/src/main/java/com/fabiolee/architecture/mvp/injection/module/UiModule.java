package com.fabiolee.architecture.mvp.injection.module;

import com.fabiolee.architecture.mvp.ui.userlist.UserListActivityModule;

import dagger.Module;

/**
 * @author fabiolee
 */
@Module(includes = {
        UserListActivityModule.class
})
public class UiModule {
}
