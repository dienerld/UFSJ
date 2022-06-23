import {
  Column,
  CreateDateColumn,
  Entity,
  PrimaryGeneratedColumn,
  UpdateDateColumn,
} from 'typeorm';

@Entity('person')
export class PersonModel {
  @PrimaryGeneratedColumn('uuid')
  id: string;

  @Column('varchar')
  name: string;

  @Column('int')
  age: number;

  @Column('varchar')
  email: string;

  @CreateDateColumn()
created_at: Date;

  @UpdateDateColumn()
  updated_at: Date;
}
