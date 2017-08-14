package net.azurewebsites.streambeta.yandexstreamsandroid.domain.view.interfaces;

import net.azurewebsites.streambeta.yandexstreamsandroid.core.View;

import java.math.BigDecimal;


public interface ProfileView extends View {
    void setUnauthorisedMode();
    void setAuthorisedMode();
    void setBalance(BigDecimal amount);
}
