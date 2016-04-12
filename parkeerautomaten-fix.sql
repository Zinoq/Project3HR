-- Om te fixen
SET SQL_SAFE_UPDATES = 0;

delete from standard.parkeerautomaten WHERE Inventarisnr=142.0;
delete FROM standard.parkeerautomaten WHERE Y_Coord > 45;


-- On-commenten om te checken of het is gelukt 

-- SELECT Inventarisnr, X_Coord, Y_Coord FROM standard.parkeerautomaten WHERE X_Coord < 45;
-- SELECT Inventarisnr, X_Coord, Y_Coord FROM standard.parkeerautomaten WHERE Y_Coord > 45;

-- Als je met alles klaar bent
-- SET SQL_SAFE_UPDATES = 1;
