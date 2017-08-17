package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces;

import com.yandex.money.api.methods.wallet.AccountInfo;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;

import java.math.BigDecimal;


public interface ProfileView extends View {
    void setUnauthorisedMode();
    void setAuthorisedMode();
    void setBalance(BigDecimal amount);
    void saveAccountInfo(AccountInfo accountInfo);
}
