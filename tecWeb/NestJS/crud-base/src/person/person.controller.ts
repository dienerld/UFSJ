import {
  Body,
  Controller, Delete, Get, NotFoundException, Param, ParseUUIDPipe, Post, Put, ValidationPipe
} from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { PersonDTO } from './person.dto';
import { PersonModel } from './person.model';

@Controller('person')
export class PersonController {
  constructor(
    @InjectRepository(PersonModel) private readonly repository: Repository<PersonModel>,
  ) {}

  @Post()
  async create(@Body(ValidationPipe) body: PersonDTO):Promise<any> {
    const person = this.repository.create(body);
    await this.repository.save(person);

    return { data: person };
  }

  @Get(':id')
  async getOne(@Param('id', ParseUUIDPipe) id: string): Promise<any> {
    const person = await this.repository.findOne({ where: { id } });

    if (!person) throw new NotFoundException('Person not found');

    return { data: person };
  }

  @Get()
  async getAll(): Promise<{data: PersonModel[]}> {
    const list = await this.repository.find();

    return { data: list };
  }

  @Put(':id')
  async update(@Param('id', ParseUUIDPipe) id: string, @Body(ValidationPipe) body: PersonDTO): Promise<{data: PersonModel}> {
    const person = await this.repository.findOne({ where: { id } });

    if (!person) throw new NotFoundException('Person not found');
    await this.repository.update(id, body);

    return { data: person };
  }

  @Delete(':id')
  delete(data: any): void {
    return;
  }
}
