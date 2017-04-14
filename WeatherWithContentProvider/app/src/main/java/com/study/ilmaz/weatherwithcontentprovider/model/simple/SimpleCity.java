package com.study.ilmaz.weatherwithcontentprovider.model.simple;


import com.study.ilmaz.weatherwithcontentprovider.model.net.City;

public class SimpleCity {
    private Integer id;
    private String name;
    private Double temp;
    private Double pressure;
    private Double humidity;
    private String icon;

    public SimpleCity(City city) {
        id = city.getId();
        name = city.getName();
        temp = city.getMain().getTemp();
        pressure = city.getMain().getPressure();
        humidity = city.getMain().getHumidity();
        icon = city.getWeather().get(0).getIcon();
    }

    public SimpleCity(Integer id, String name, Double temp, Double pressure, Double humidity, String icon) {
        this.id = id;
        this.name = name;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleCity city = (SimpleCity) o;

        if (id != null ? !id.equals(city.id) : city.id != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (temp != null ? !temp.equals(city.temp) : city.temp != null) return false;
        if (pressure != null ? !pressure.equals(city.pressure) : city.pressure != null)
            return false;
        if (humidity != null ? !humidity.equals(city.humidity) : city.humidity != null)
            return false;
        return icon != null ? icon.equals(city.icon) : city.icon == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }
}
