import instruments.Anemometer;
import instruments.Barometer;
import instruments.Hygrometer;
import instruments.Thermometer;
import instruments.satellite.SatelliteUplink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherStationTest {
    WeatherStation weatherStation;
    Anemometer anemometerMock;
    Barometer barometerMock;
    Hygrometer hygrometerMock;
    Thermometer thermometerMock;
    SatelliteUplink satelliteUplinkSpy;

    @BeforeEach
    public void setUp() {
        weatherStation = new WeatherStation();
        anemometerMock = Mockito.mock(Anemometer.class);
        barometerMock = Mockito.mock(Barometer.class);
        hygrometerMock = Mockito.mock(Hygrometer.class);
        thermometerMock = Mockito.mock(Thermometer.class);
        satelliteUplinkSpy = Mockito.spy(new SatelliteUplink());

        weatherStation.setAnemometer(anemometerMock);
        weatherStation.setBarometer(barometerMock);
        weatherStation.setHygrometer(hygrometerMock);
        weatherStation.setThermometer(thermometerMock);
        weatherStation.setSatelliteUplink(satelliteUplinkSpy);
    }

    @Test
    public void testRunStormWarningCheck() {
        Mockito.when(hygrometerMock.getCurrentHumidity()).thenReturn(35.7).thenReturn(20.0);
        Mockito.when(barometerMock.getAtmosphericPressure()).thenReturn(800.0).thenReturn(910.0);
        Mockito.when(thermometerMock.getCurrentTemperature()).thenReturn(46.0).thenReturn(80.0);

        boolean result1 = weatherStation.runStormWarningCheck();
        boolean result2 = weatherStation.runStormWarningCheck();

        Mockito.verify(hygrometerMock, Mockito.times(2)).getCurrentHumidity();
        Mockito.verify(barometerMock, Mockito.times(2)).getAtmosphericPressure();
        Mockito.verify(thermometerMock, Mockito.times(2)).getCurrentTemperature();

        Mockito.verify(satelliteUplinkSpy).runStormCheckForArea(35.7, 800.0, 46.0);
        Mockito.verify(satelliteUplinkSpy).runStormCheckForArea(20.0, 910.0, 80.0);

        Mockito.verify(satelliteUplinkSpy).checkNearbyAreaStorms();

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testRunTornadoWarningCheck() {
        Mockito.when(hygrometerMock.getCurrentHumidity()).thenReturn(35.7).thenReturn(30.0);
        Mockito.when(barometerMock.getAtmosphericPressure()).thenReturn(800.0).thenReturn(1000.0);
        Mockito.when(anemometerMock.getWindSpeed()).thenReturn(40.0).thenReturn(20.0);

        boolean result1 = weatherStation.runTornadoWarningCheck();
        boolean result2 = weatherStation.runTornadoWarningCheck();

        Mockito.verify(hygrometerMock, Mockito.times(2)).getCurrentHumidity();
        Mockito.verify(barometerMock, Mockito.times(2)).getAtmosphericPressure();
        Mockito.verify(anemometerMock, Mockito.times(2)).getWindSpeed();

        Mockito.verify(satelliteUplinkSpy).runTornadoCheckForArea(35.7, 800.0, 40.0);
        Mockito.verify(satelliteUplinkSpy).runTornadoCheckForArea(30.0, 1000.0, 20.0);

        Mockito.verify(satelliteUplinkSpy).checkNearbyAreaTornadoes();

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testAnemometerCalibrationCheck() {
        Mockito.when(anemometerMock.getWindSpeed()).thenReturn(20.0).thenReturn(20.0).thenReturn(40.0).thenReturn(50.0);
        Mockito.when(anemometerMock.getWindDirInDegrees()).thenReturn(40.0).thenReturn(40.0).thenReturn(80.0).thenReturn(90.0);

        boolean result1 = weatherStation.anemometerCalibrationCheck();
        boolean result2 = weatherStation.anemometerCalibrationCheck();

        Mockito.verify(anemometerMock, Mockito.times(4)).getWindSpeed();
        Mockito.verify(anemometerMock, Mockito.times(4)).getWindDirInDegrees();

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testTemperatureCalibrationTest() {
        Mockito.when(thermometerMock.getCurrentTemperature()).thenReturn(20.0).thenReturn(20.0).thenReturn(40.0).thenReturn(50.0);

        boolean result1 = weatherStation.temperatureCalibrationTest();
        boolean result2 = weatherStation.temperatureCalibrationTest();

        Mockito.verify(thermometerMock, Mockito.times(4)).getCurrentTemperature();

        assertTrue(result1);
        assertFalse(result2);
    }
}
