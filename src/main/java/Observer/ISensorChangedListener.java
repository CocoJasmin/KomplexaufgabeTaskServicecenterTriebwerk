package Observer;

import Basis.Engine;

public interface ISensorChangedListener {
    void updateSensorStatus(Engine engine, TelemetryValue percentage);
}
