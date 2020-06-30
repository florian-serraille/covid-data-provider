create table country
(
    iso_code                        varchar(3)  not null comment 'ISO 3166-1 alpha-3 – three-letter country codes' primary key,
    full_name                       varchar(40) not null,
    continent                       varchar(20) not null
);

create table report
(
    id                              int         auto_increment  not null comment 'Surrogate key' primary key,
    report_date                     date        not null comment 'Date of observation - Our World in Data',
    country_iso_code                varchar(3)  not null comment 'ISO 3166-1 alpha-3 – three-letter country codes',
    total_cases                     int         not null comment 'Total confirmed cases of COVID-19',
    new_cases                       int         not null comment 'New confirmed cases of COVID-19',
    total_deaths                    int         not null comment 'Total deaths attributed to COVID-19',
    new_deaths                      int         not null comment 'New deaths attributed to COVID-19 ',
    total_cases_per_million         decimal     not null comment 'Total confirmed cases of COVID-19 per 1,000,000 people',
    new_cases_per_million           decimal     not null comment 'New confirmed cases of COVID-19 per 1,000,000 people',
    total_deaths_per_million        decimal     not null comment 'Total deaths attributed to COVID-19 per 1,000,000 people',
    new_deaths_per_million          decimal     not null comment 'New deaths attributed to COVID-19 per 1,000,000 people',
    total_tests                     int         comment 'Total tests for COVID-19',
    new_tests                       int         comment 'New tests for COVID-19',
    new_tests_smoothed              int         comment 'New tests for COVID-19 (7-day smoothed). For countries that don''t report testing data on a daily basis, we assume that testing changed equally on a daily basis over any periods in which no data was reported. This produces a complete series of daily figures, which is then averaged over a rolling 7-day window',
    total_tests_per_thousand        decimal     comment 'Total tests for COVID-19 per 1,000 people',
    new_tests_per_thousand          decimal     comment 'New tests for COVID-19 per 1,000 people',
    new_tests_smoothed_per_thousand decimal     comment 'New tests for COVID-19 (7-day smoothed) per 1,000 people',
    tests_units                     varchar(30) comment 'National government reports',
    stringency_index                int         comment 'Government Response Stringency Index: composite measure based on 9 response indicators including school closures, workplace closures, and travel bans, rescaled to a value from 0 to 100 (100 = strictest response)',
    population                      int         comment 'Population number',
    population_density              decimal     comment 'Number of people divided by land area, measured in square kilometers, most recent year available',
    median_age                      decimal     comment 'Median age of the population',
    aged_65_older                   decimal     comment 'Share of the population that is 65 years and older, most recent year available',
    aged_70_older                   decimal     comment 'Share of the population that is 70 years and older',
    gpd_per_capita                  decimal     comment 'Gross domestic product at purchasing power parity (constant 2011 international dollars), most recent year available',
    extreme_poverty                 decimal     comment 'Share of the population living in extreme poverty, most recent year available since 2010',
    cvd_death_rate                  decimal     comment 'Death rate from cardiovascular disease in 2017',
    diabetes_prevalence             decimal     comment 'Diabetes prevalence (% of population aged 20 to 79) in 2017',
    female_smokers                  decimal     comment 'Share of women who smoke, most recent year available',
    male_smokers                    decimal     comment 'Share of men who smoke, most recent year available',
    hand_washing_facilites          decimal     comment 'Share of the population with basic handwashing facilities on premises, most recent year available',
    hospital_beds_per_thousand      decimal     comment 'Hospital beds per 1,000 people, most recent year available since 2010	',
    life_expectancy                 decimal     comment 'Life expectancy at birth in 2019',
    constraint country_iso_code_date_index
        unique (country_iso_code, report_date),
    constraint country_iso_code_fk
        foreign key (country_iso_code) references country (iso_code)
);
