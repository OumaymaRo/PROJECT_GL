-- Mettre à jour la colonne rapport pour accepter plus de caractères
ALTER TABLE panne MODIFY COLUMN rapport VARCHAR(4000);

-- Supprimer la colonne rapport_reparation si elle existe
ALTER TABLE panne DROP COLUMN IF EXISTS rapport_reparation; 